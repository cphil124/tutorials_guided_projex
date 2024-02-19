FROM python:3.8-slim

ADD requirements.txt .

RUN set -ex; \
    pip install --no-cache-dir -r requirements.txt

WORKDIR /

ADD wait_for_it.sh .

ADD python_producer.py .

CMD ./wait_for_it.sh -s -t 30 $ZOOKEEPER_SERVER -- ./wait_for_it.sh -s -t 30 $KAFKA_SERVER -- python -u python_producer.py