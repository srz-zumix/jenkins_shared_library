pipelineJob('Test_szCreateQRCode') {
  definition {
    cps {
        sandbox(true)
        script("""
@Library('srz-zumix-shared-library') _

pipeline {
  agent any

  stages {
    stage("Test") {
      steps {
        szCreateQRCode("https://github.com/srz-zumix/jenkins_shared_library", 'qrcode.png', widht: 200, height: 200)
      }
    }
  }
}
""")
    }
  }
}
