def plugins = jenkins.model.Jenkins.instance.getPluginManager().getPlugins()
def plugins_list = []
def depends_list = []
plugins.each {
    def name = it.getShortName()
    plugins_list.add(name)
    def deps =  it.getDependencies()
    deps.each {
      def s = it.shortName
      depends_list.add(s)
    }
} 

def repr = plugins_list.toSet() - depends_list.toSet()
plugins.each {
  if( it.getShortName() in repr ) {
    println ("${it.getShortName()}:${it.getVersion()}")
  }
}
return repr
