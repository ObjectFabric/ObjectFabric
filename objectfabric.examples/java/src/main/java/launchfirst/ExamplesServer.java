/**
 * This file is part of ObjectFabric (http://objectfabric.org).
 *
 * ObjectFabric is licensed under the Apache License, Version 2.0, the terms
 * of which may be found at http://www.apache.org/licenses/LICENSE-2.0.html.
 * 
 * Copyright ObjectFabric Inc.
 * 
 * This file is provided AS IS with NO WARRANTY OF ANY KIND, INCLUDING THE
 * WARRANTY OF DESIGN, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */

package launchfirst;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;
import org.objectfabric.Counter;
import org.objectfabric.FileSystem;
import org.objectfabric.JVMServer;
import org.objectfabric.JVMWorkspace;
import org.objectfabric.NettySession;
import org.objectfabric.Resource;
import org.objectfabric.Server;
import org.objectfabric.TArrayInteger;
import org.objectfabric.TMap;
import org.objectfabric.TSet;
import org.objectfabric.Workspace;

import part05.generated.Car;
import part05.generated.ElectricCar;
import part05.generated.Settings;
import part05.generated.User;

/**
 * Simple stand-alone server.
 */
public class ExamplesServer {

    public static void main(String[] args) {
        clearFolder("temp/server");

        /*
         * Data will be stored in a folder.
         */
        FileSystem store = new FileSystem("temp/server");

        /*
         * Create a workspace to add resources to the folder.
         */
        Workspace workspace = new JVMWorkspace();
        workspace.addURIHandler(store);

        /*
         * Add resources, first a simple String.
         */
        workspace.resolve("/helloworld").set("Hello World!");

        /*
         * Add a map. ObjectFabric maps derive from TObject, whose constructor requires
         * the resource they will be stored at. Resolve the resource first and pass it.
         */
        Resource resource = workspace.resolve("/map");
        TMap<String, Integer> map = new TMap<String, Integer>(resource);
        map.put("example key", 42);
        resource.set(map);

        /*
         * Add various types for the ObjectModel example.
         */
        workspace.resolve("/string").set("{\"key\": \"value\"}");
        workspace.resolve("/int").set(1);
        workspace.resolve("/bin").set(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7 });

        resource = workspace.resolve("/set");
        TSet<String> set = new TSet<String>(resource);
        set.add("blah");
        resource.set(set);

        resource = workspace.resolve("/arrayOfInt");
        TArrayInteger ints = new TArrayInteger(resource, 10);
        ints.set(5, 1);
        resource.set(ints);

        resource = workspace.resolve("/counter");
        Counter counter = new Counter(resource);
        counter.add(1);
        resource.set(counter);

        /*
         * Custom objects.
         */
        resource = workspace.resolve("/car");
        Car car = new Car(resource, "DeLorean");
        User joe = new User(resource);
        joe.name("Joe");
        car.driver(joe);
        User friend = new User(resource);
        Settings settings = new Settings(resource);
        settings.seatHeight(5);
        car.settings().put(friend, settings);
        car.child(new ElectricCar(resource, "Tesla"));
        resource.set(car);

        /*
         * Generics. For better interoperability with platforms which have runtime
         * generics, some classes have type arguments. E.g this code creates a set that
         * would be instantiated on .NET as a strongly typed Set<Car>.
         */
        resource = workspace.resolve("/generics");
        resource.set(new TSet<Car>(resource, Car.TYPE));

        /*
         * Close workspace to avoids wasting resources maintaining objects up to date as
         * they are not used anymore in this process.
         */
        workspace.close();

        /*
         * Use 'temp' folder as default handler for all URIs.
         */
        final Server server = new JVMServer();
        server.addURIHandler(store);

        /*
         * Start a WebSocket server. (C.f. https://netty.io)
         */
        ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory( //
                Executors.newCachedThreadPool(), Executors.newCachedThreadPool()));

        bootstrap.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("decoder", new HttpRequestDecoder());
                pipeline.addLast("encoder", new HttpResponseEncoder());
                pipeline.addLast("objectfabric", new NettySession(server));
                return pipeline;
            }
        });

        /*
         * Use port 8888 instead of 8080 to avoid conflicts with GWT and JS samples.
         */
        bootstrap.bind(new InetSocketAddress(8888));
        System.out.println("Started ExamplesServer on port 8888");

        try {
            Thread.sleep(Long.MAX_VALUE);
        } catch (InterruptedException _) {
        }
    }

    private static void clearFolder(String folder) {
        clearFolder(new File(folder));
    }

    private static void clearFolder(File folder) {
        if (folder.exists()) {
            for (File child : folder.listFiles()) {
                if (child.isDirectory())
                    clearFolder(child);

                if (!child.delete())
                    throw new RuntimeException();
            }
        }
    }
}
