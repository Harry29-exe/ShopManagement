IMG_NAME="shop_management_db"
CONTAINER_NAME=${IMG_NAME}

# -q quiet, -a all, -f filter
if [ "$(docker ps -q -f name=${CONTAINER_NAME})" ]; then
  echo "stopping container: ${CONTAINER_NAME}"
  docker container kill ${CONTAINER_NAME}
fi

# -q quiet, -a all, -f filter
if [ "$(docker ps -q -a -f name=${CONTAINER_NAME})" ]; then
  echo "removing container: ${CONTAINER_NAME}"
  docker container rm ${CONTAINER_NAME}
fi

if [ "$(docker images -q ${IMG_NAME})" ]; then
  echo "removing image: ${IMG_NAME}"
    docker rm ${IMG_NAME}
fi
docker build -t ${IMG_NAME} ./../

docker run -d -p 5432:5432 --name ${CONTAINER_NAME} ${IMG_NAME}
echo "Container: ${CONTAINER_NAME} should be running now"
