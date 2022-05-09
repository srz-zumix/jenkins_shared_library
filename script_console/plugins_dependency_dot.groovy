def plugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins()
println "digraph plugins {"
plugins.each {
    def plugin = it.getShortName()
    println "\"${plugin}\";"
    def deps =  it.getDependencies()
    deps.each {
      def s = it.shortName
      println "\"${plugin}\" -> \"${s}\";"
    }
} 
println "}"
