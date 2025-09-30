# Makefile simple para Spring Boot + Docker

GRADLE = ./gradlew
DOCKER = docker
APP_NAME = adal-tigo-app
VERSION ?= $(shell grep '^version=' gradle.properties | cut -d'=' -f2 | xargs)

.PHONY: build docker-build docker-run get-version set-version

# Build del proyecto sin tests
build:
	$(GRADLE) clean build -x test

# Docker build
docker-build: build
	$(DOCKER) build -t $(APP_NAME):$(VERSION) .

# Run Docker container
docker-run:
	$(DOCKER) run -p 8080:8080 $(APP_NAME):$(VERSION)

# Mostrar versión
get-version:
	@echo $(VERSION)

# Actualizar versión en gradle.properties
set-version:
	@if [ -z "$(VERSION)" ]; then \
		echo "Error: VERSION no está definido"; \
		exit 1; \
	fi
	perl -pi -e "s/^version=.*/version=$(VERSION)/" gradle.properties
	@echo "Versión actualizada a $(VERSION)"
