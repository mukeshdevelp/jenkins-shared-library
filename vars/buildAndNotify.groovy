import org.example.MavenHelper

def call(Map config = [:]) {
    def repoUrl = config.get('repoUrl', '')
    def branch = config.get('branch', 'main')
    def mavenCommand = config.get('mavenCommand', 'clean install')
    def notifyType = config.get('notifyType', 'console') // console, slack, telegram
    def slackChannel = config.get('slackChannel', '')

    def buildStatus = 'SUCCESS'
    try {
        stage('Checkout') {
            checkout([
                $class: 'GitSCM',
                branches: [[name: branch]],
                userRemoteConfigs: [[url: repoUrl]]
            ])
        }
        stage('Build') {
            sh "mvn ${mavenCommand}"
        }
    } catch (err) {
        buildStatus = 'FAILURE'
        throw err
    } finally {
        def message = MavenHelper.buildInfo(repoUrl, branch, mavenCommand, buildStatus)
        if (notifyType == 'slack' && slackChannel) {
            slackSend channel: slackChannel,
                color: (buildStatus == 'SUCCESS') ? '#36a64f' : '#ff0000',
                message: message
        } else if (notifyType == 'telegram') {
            // Replace with your Telegram notification logic, e.g. httpRequest step
        } else {
            echo message
        }
    }
}
