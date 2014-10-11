package org.onlab.onos.cli;

import org.apache.karaf.shell.commands.Command;
import org.onlab.onos.CoreService;
import org.onlab.onos.cluster.ClusterService;
import org.onlab.onos.net.device.DeviceService;
import org.onlab.onos.net.flow.FlowRuleService;
import org.onlab.onos.net.host.HostService;
import org.onlab.onos.net.intent.IntentService;
import org.onlab.onos.net.link.LinkService;
import org.onlab.onos.net.topology.Topology;
import org.onlab.onos.net.topology.TopologyService;

/**
 * Provides summary of ONOS model.
 */
@Command(scope = "onos", name = "summary",
         description = "Provides summary of ONOS model")
public class SummaryCommand extends AbstractShellCommand {

    @Override
    protected void execute() {
        TopologyService topologyService = get(TopologyService.class);
        Topology topology = topologyService.currentTopology();
        print("version=%s, nodes=%d, devices=%d, links=%d, hosts=%d, clusters=%s, paths=%d, flows=%d, intents=%d",
              get(CoreService.class).version().toString(),
              get(ClusterService.class).getNodes().size(),
              get(DeviceService.class).getDeviceCount(),
              get(LinkService.class).getLinkCount(),
              get(HostService.class).getHostCount(),
              topologyService.getClusters(topology).size(),
              topology.pathCount(),
              get(FlowRuleService.class).getFlowRuleCount(),
              get(IntentService.class).getIntentCount());
    }

}