JAR_FILE = BootlegOsu.jar
IMAGE_NAME = bootleg-osu

.PHONY: clean
clean:
	find . \
		-type f \
		-name "*.class" \
		-exec \
			rm --verbose --force {} \;
	rm -vf $(JAR_FILE)

.PHONY: compile
compile:
	javac -verbose -Xlint:unchecked src/*.java

.PHONY: build-jar
build-jar: | compile
	cd src && \
	jar cvfe $(JAR_FILE) Launcher *.class && \
    mv $(JAR_FILE) ../$(JAR_FILE)

.PHONY: docker-build
docker-build:
	docker build \
		--tag $(IMAGE_NAME) \
		.

.PHONY: run-jar
run-jar: | build-jar
	java -jar $(JAR_FILE)

.PHONY: run-class
run-class: | compile
	java -classpath src/ Launcher

.PHONY: run-docker
run-docker: | docker-build
	docker run \
		--detach \
		--env DISPLAY=$$DISPLAY \
		--volume /tmp/.X11-unix:/tmp/.X11-unix \
		$(IMAGE_NAME)

.PHONY: docker-kill 
docker-kill:
	docker stop $$(docker ps | awk -v i="^$(IMAGE_NAME).*" '{if($$2~i){print$$1}}')

.PHONY: bash-into-docker
bash-into-docker: | docker-build
	docker run \
		--interactive \
		--tty \
		--rm \
		--entrypoint bash \
		$(IMAGE_NAME)
