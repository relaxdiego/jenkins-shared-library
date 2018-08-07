package org.yourcompany

class ReleaseIDGenerator implements Serializable {

    def script

    def ReleaseIDGenerator(script) {
        this.script = script
    }

    def generate() {
        def version = script.readFile('VERSION').trim()
        def buildID = script.env.BUILD_ID
        def shortSha = script.env.GIT_COMMIT.take(7)

        return "${version}+b${buildID}.${shortSha}"
    }
}
