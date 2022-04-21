IMG_NAME="sm-nginx"
CONTAINER_NAME=$IMG_NAME
echo $CONTAINER_NAME

if [ ! "$(docker images -q ${IMG_NAME})" ]; then
  docker build -t ${IMG_NAME} ./
fi

# -q quiet, -a all, -f filter
if [ ! "$(docker ps -q -a -f name=${CONTAINER_NAME})" ]; then
  docker run -p 443:443 -p 4430:4430 --network="host" --name ${IMG_NAME} ${CONTAINER_NAME}
fi
