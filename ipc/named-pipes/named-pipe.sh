#!/bin/bash

BACKING_FILE="named-pipe-test.txt"

usage () {
    echo "Usage:"
    echo "        named-pipe.sh server"
    echo "        named-pipe.sh client"
    exit 1
}

if [ ${#@} -ne 1 ]; then
    usage
fi

if [ ${1} == "server" ]; then
    echo "Started the server {create & cat named pipe \"${BACKING_FILE}\"} ..."
    mkfifo ${BACKING_FILE}
    cat ${BACKING_FILE}
    echo "Stopping the server {remove named pipe \"${BACKING_FILE}\"} ..."
    unlink ${BACKING_FILE}
elif [ ${1} == "client" ]; then
    echo "Started the client {write stdin to backing file \"${BACKING_FILE}\"} ..."
    cat > ${BACKING_FILE}
else
    usage
fi

