package client;

import com.me.gRPC.User;
import com.me.gRPC.userGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.sql.SQLOutput;
import java.util.Scanner;

public class GrpcClient {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Select \n1.to register to the server\n2.to login to the server");
            int choice = input.nextInt();
            if (choice == 2) {
                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 12345).usePlaintext().build();


                userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);

                String username = input.next();
                String pass = input.next();
                User.LoginRequest loginRequest = User.LoginRequest.newBuilder().setUsername(username).setPassword(pass).build();
                User.APIResponse response = userStub.login(loginRequest);
                System.out.println(response.getResponseMessage());

            } else {
                ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 12345).usePlaintext().build();


                userGrpc.userBlockingStub userStub = userGrpc.newBlockingStub(channel);

                String username = input.next();
                String pass = input.next();
                User.RegisterReq registerRequest = User.RegisterReq.newBuilder().setUsername(username).setPassword(pass).build();
                User.APIResponse response = userStub.register(registerRequest);
                System.out.println(response.getResponseMessage());

            }

        }
    }
}
