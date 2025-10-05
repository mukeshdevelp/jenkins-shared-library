package org.example

class MavenHelper implements Serializable {
    static String buildInfo(String repoUrl, String branch, String cmd, String status) {
        return "Repo: ${repoUrl}, Branch: ${branch}, Cmd: ${cmd}, Status: ${status}"
    }
}
