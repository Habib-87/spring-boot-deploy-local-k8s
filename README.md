# spring-boot-deploy-local-k8s
This very Simple Spring Boot Application to demonstrate how to deploy in local Kubernetes. Application doesnt have the standard structure or unit test cases except just one controller. The main purpose of the project is to show how can you build docker images of your spring boot application and push it to local container registry. If you wish to push the images to docker registry you can do that. We have defined some basics Kubernetes resources like Deployment and Service. 

I have mentioned below the steps .. 

Prerequisites: 

Docker Desktop for Windows or Mac. You can install Minikube or you can just enable Kuberenets in the docker desktop. Here is the link for Windows user about how to install Docker desktop and enable Kubernetes.

	https://docs.docker.com/docker-for-windows/

## Steps

1.Define the Dockerfile in the project. 

2.Once you are done build the application with maven by mvn clean package.

3.Create the Docker image for this application by running the command 

	docker build -t spring-boot-app .

4.Build a local container registry by running the below command 

	docker run -d -p 5000:5000 --restart=always --name registry registry:2

5.Tag the newly built docker image 
	
	docker tag spring-boot-app localhost:5000/spring-boot-app
	
6.Push the image to the local registry by 

	docker push localhost:5000/spring-boot-app	

7.If you want to push the image to the docker registry the you have to run the below   commands instead of 4 and 5 steps.

		docker login - provide your docker id and password to login
		Tag the image by running ::
		docker tag spring-boot-ap <username>/spring-boot-app
		Push the image::
		docker push <username>/spring-boot-app
		
8.Now docker image is ready for your application and its been pushed to the registry.Lets see how kuberenets resources we have to define. Please have a look the file name appdeploy.yml.
We have defined two resources .
 Deployment - To fetch the image from the registry and deploy the specified replica containers. We have defined some basic readiness and liveness probe That can be improved better.
 
 Service - NodePort service to access the deployment conatiners or pod outside of cluster. If it is cloud the we have to define it as a ingress. Since it is local deployment thats why we will go ahead with NodePort.
 Once you define the resources please run 
			
			kubectl apply -f appdeploy.yml 

 9.You can see the resources by running 
	
				kubectl get pods	
				kubectl get svc
				kubectl get deploy	
	
10. To test the application run the below command in the command prompt 

	curl -v http://localhost:31000/simple/api - 31000 is defined in Service as a nodePort
	
	or run in the browser http://localhost:31000/simple/api