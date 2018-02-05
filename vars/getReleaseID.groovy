import org.yourcompany.ReleaseIDGenerator

def call() {
    def generator = new ReleaseIDGenerator(this)
    return generator.generate()
}
