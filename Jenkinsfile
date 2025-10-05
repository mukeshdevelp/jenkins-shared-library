@Library('jenkins-shared-library') _
import org.example.MavenHelper
buildAndNotify(
  repoUrl: 'https://github.com/example/maven-app.git',
  branch: 'develop',
  mavenCommand: 'clean package',
  notifyType: 'slack',
  slackChannel: '#ci-alerts'
)
