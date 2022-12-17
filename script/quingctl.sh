#!/bin/bash

APP_HOME="/root/quing"
ROOT_HOME="/root"
LOG_FILE=$ROOT_HOME"/run.out"
PID_FILE=$ROOT_HOME"/quing.pid"
PS=$(ps -ef | grep java | grep quing)
JAVA_HOME=/usr/lib/jvm/java-11

# JVM_OPTIONS="-Xms512M -Xmx1G -XX:+UseParallelGC"
JVM_OPTIONS=""

JAR_PATH="/root/quing/build/libs/"
JAR_FILE="quing-0.0.1-SNAPSHOT.jar"
JAR_FULLPATH=$JAR_PATH$JAR_FILE

# SPRING_CONF_DIR="-Dspring.config.additional-location=$(pwd)/conf/ "
SPRING_CONF_DIR=""
SPRING_ACTIVE_PROFILE="-Dspring.profiles.active=prod "
SPRING_OPTIONS="$SPRING_CONF_DIR$SPRING_ACTIVE_PROFILE"

start () {
  PS_COUNT=$(ps -ef |grep quing | grep java | grep -v watch | wc -l)
  if [ 1 -eq $PS_COUNT ]; then
    if [ $(cat $PID_FILE) -eq $(ps -ef |grep java |grep quing | grep -v watch |  awk '{print $2}') ]; then
      echo "Quing Already Running"
      exit 1
    else
      echo "비정상종료.. clean up and start"
      rm $PID_FILE
      rm $LOG_FILE
      pkill java
      sleep 3
    fi

  fi

  echo "git pull"
  cd $APP_HOME
  git pull

  nohup $JAVA_HOME/bin/java -jar $JAR_FULLPATH $JVM_OPTIONS $SPRING_OPTIONS >$LOG_FILE 2>&1 &
  echo "$!" >$PID_FILE

  sleep 1

  if [ 1 -eq $(ps -ef |grep `cat $PID_FILE` |grep java | grep -v watch | wc -l) ]; then
    echo $(cat $PID_FILE)
    echo "Quing Service Started."
  else
    echo "Starting fail.. clean up"
    rm $PID_FILE
    rm $LOG_FILE
    exit 1
  fi
}

stop() {
    PS_COUNT=$(ps -ef |grep quing | grep java | grep -v watch | wc -l)

    if [ -f $PID_FILE ]; then
      kill $(cat $PID_FILE)

      sleep 1

      while [ 1 -le $PS_COUNT ]; do
        PS_COUNT=$(ps -ef |grep quing | grep java |grep -v watch | wc -l)
        echo "kill.."
        status
        sleep 5
      done

      rm $PID_FILE
      rm $LOG_FILE

      echo "Quing Service Stopped."

    else
      echo "Quing isn't running."
      exit 1
    fi
}

status(){
  echo "PID_FILE : "$(cat $PID_FILE)
  echo $PS
}

case "$1" in
start)
  start
  ;;
stop)
  stop
  ;;
status)
  status
  ;;
restart)
  stop
  start
  ;;
*)
  echo "Usage: $0 {start|stop|restart|status}"
  exit 1
  ;;
esac
