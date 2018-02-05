package org.yourcompany

class ReleaseIDGeneratorTest extends GroovyTestCase {

    // We're stubbing out a pipeline script. This one pretends to be
    // a script that's running against the master branch.
    class MasterPipelineScript {
        def version = ""
        def requestedVersionFilename = ""

        def env = [
            'BUILD_ID': 15,
            'GIT_COMMIT': 'a3bb4b7f9bf5db1c436b96a970c04d553feed1c5'
        ]

        def readFile(versionFilename) {
            this.requestedVersionFilename = versionFilename
            return this.version
        }

        def MasterPipelineScript(version) {
            this.version = version
        }
    }

    //
    // Tests
    //

    void testMasterPipelineHappyPath() {
        def version = '1.2.3'
        def pipeline = new MasterPipelineScript(version)
        def shortSha = pipeline.env.GIT_COMMIT.take(7)
        def expectedReleaseID = "${version}+b${pipeline.env.BUILD_ID}.${shortSha}"

        def returnedReleaseID = new ReleaseIDGenerator(pipeline).generate()

        assert 'VERSION' == pipeline.requestedVersionFilename
        assert expectedReleaseID == returnedReleaseID
    }
}
