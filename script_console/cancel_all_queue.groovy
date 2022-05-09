def q = Jenkins.instance.queue
q.items.findAll { q.cancel(it.task) }
