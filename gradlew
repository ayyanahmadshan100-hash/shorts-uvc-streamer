#!/usr/bin/env sh

# Ensure the script stops on error
set -e

# Download the gradle configuration architecture block automatically if missing
if [ ! -d "gradle/wrapper" ]; then
    mkdir -p gradle/wrapper
    curl -sL https://gradle.org -o gradle-8.2-bin.zip
    
    # Inject temporary configuration parameters for the system wrapper
    cat << 'EOF' > gradle/wrapper/gradle-wrapper.properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.2-bin.zip
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
EOF
fi

# Execute localized system environment check parameter parameters
exec gradle "$@"
