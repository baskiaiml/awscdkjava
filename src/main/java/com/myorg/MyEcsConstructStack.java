/**
 * 
 */
package com.myorg;

import software.amazon.awscdk.StackProps;
import software.amazon.awscdk.services.ec2.Vpc;
import software.amazon.awscdk.services.ecs.Cluster;
import software.amazon.awscdk.services.ecs.ContainerImage;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedFargateService;
import software.amazon.awscdk.services.ecs.patterns.ApplicationLoadBalancedTaskImageOptions;
import software.constructs.Construct;

/**
 * @author 91994
 *
 */
public class MyEcsConstructStack {

	/**
	 * @param args
	 */
	 public MyEcsConstructStack(final Construct scope, final String id) {
	        this(scope, id, null);
	    }

	    public MyEcsConstructStack(final Construct scope, final String id,
	            StackProps props) {
	        super();

	        Vpc vpc = Vpc.Builder.create(scope, "MyVpc").maxAzs(3).build();

	        Cluster cluster = Cluster.Builder.create(scope, "MyCluster")
	                .vpc(vpc).build();

	        ApplicationLoadBalancedFargateService.Builder.create(scope, "MyFargateService")
	                .cluster(cluster)
	                .cpu(512)
	                .desiredCount(6)
	                .taskImageOptions(
	                       ApplicationLoadBalancedTaskImageOptions.builder()
	                               .image(ContainerImage
	                                       .fromRegistry("amazon/amazon-ecs-sample"))
	                               .build()).memoryLimitMiB(2048)
	                .publicLoadBalancer(true).build();
	    }
	}
