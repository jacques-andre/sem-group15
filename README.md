![](https://github.com/jacques-andre/sem-group15/actions/workflows/docker-image.yml/badge.svg)

## Docker:

**Build an image:**
``docker build -t NAME .``

**Run built image:**
``docker run NAME``

## Running docker-compose

Run compose in detached: `docker-compose up -d`
Check logs of a container: `docker logs NAME`

## Build
To buld run this: `mvn -f pom.xml clean compile assembly:single`


## Lazy Docker:
Use this to understand what is happening with the docker containers easier:

https://github.com/jesseduffield/lazydocker