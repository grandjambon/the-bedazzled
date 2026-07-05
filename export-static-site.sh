#!/usr/bin/env bash
set -euo pipefail

PORT=8080
OUTPUT_DIR="target/static-site"

echo "==> Starting application on port $PORT..."
mvn spring-boot:run -Dspring-boot.run.arguments="--server.port=$PORT" -q &
APP_PID=$!

# Wait for app to be ready
echo "==> Waiting for app to start..."
for i in $(seq 1 30); do
    if curl -s -o /dev/null http://localhost:$PORT/; then
        echo "==> App is ready."
        break
    fi
    if [ $i -eq 30 ]; then
        echo "ERROR: App did not start in time."
        kill $APP_PID 2>/dev/null
        exit 1
    fi
    sleep 1
done

echo "==> Crawling site with wget..."
rm -rf "$OUTPUT_DIR"
mkdir -p "$OUTPUT_DIR"
wget --mirror \
     --convert-links \
     --adjust-extension \
     --page-requisites \
     -nH \
     --directory-prefix="$OUTPUT_DIR" \
     --no-verbose \
     "http://localhost:$PORT/"

echo "==> Stopping application..."
kill $APP_PID
wait $APP_PID 2>/dev/null || true

echo "==> Static site exported to $OUTPUT_DIR"
echo "    To test: cd $OUTPUT_DIR && python3 -m http.server 9090"
