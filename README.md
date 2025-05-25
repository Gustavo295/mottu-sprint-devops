# 🛵 Monitoramento Mottu

## 👨‍💻 INTEGRANTES
- RM558763 • Eric Issamu de Lima Yoshida
- RM555010 • Gustavo Matias Teixeira
- RM555010 • Gustavo Monção

## 💬 Explicação do Projeto
- Este projeto é uma API RESTful para monitoramento de motos, focada em funcionalidades como localização via UWB, estado das motos, filiais e muito mais. Utiliza Spring Boot com Gradle como sistema de build.

## ⚠ Requisitos mínimos e recomendados para rodar a aplicação completa
- RAM: 3gb - Recomendado: 4gb
- CPU: 1vCPU - Recomendado: 2vCPU
- Disco: 20gb - Recomendado: 30gb

---
## 🖥 1º - Criação da VM

### Grupo de Recursos

```
az group create --name rg-vm-mottu --location brazilsouth
```

### Criação da VM
```
az vm create \
   --resource-group rg-vm-mottu \
   --name vm-mottu \
   --image almalinux:almalinux-x86_64:9-gen2:9.5.202411260 \
   --size Standard_B1ms \
   --vnet-name nnet-mottu \
   --nsg nsgsr-mottu \
   --public-ip-address pip-almalinux \
   --authentication-type password \
   --admin-username admmottu \
   --admin-password MottuSP@2025
```

```
az vm create --resource-group rg-vm-mottu --name vm-mottu --image almalinux:almalinux-x86_64:9-gen2:9.5.202411260 --size Standard_B1ms --vnet-name nnet-mottu --nsg nsgsr-mottu --public-ip-address pip-almalinux --authentication-type password --admin-username admmottu --admin-password MottuSP@2025
```
---
## 🚪 2º - Expondo portas

### Porta 22

- Múltiplas linhas
```
az network nsg rule create \
   --resource-group rg-vm-mottu \
   --nsg-name nsgsr-mottu \
   --name port_22 \
   --protocol tcp \
   --priority 1010 \
   --destination-port-range 22
```

- Uma linha
```
az network nsg rule create --resource-group rg-vm-mottu --nsg-name nsgsr-mottu --name port_22 --protocol tcp --priority 1010 --destination-port-range 22
```

### Porta 1521

- Múltiplas linhas
```
az network nsg rule create \
   --resource-group rg-vm-mottu \
   --nsg-name nsgsr-mottu \
   --name port_1521 \
   --protocol tcp \
   --priority 1020 \
   --destination-port-range 1521
```

- Uma linha
```
az network nsg rule create --resource-group rg-vm-mottu --nsg-name nsgsr-mottu --name port_1521 --protocol tcp --priority 1020 --destination-port-range 1521
```

### Porta 5500

- Múltiplas linhas
```
az network nsg rule create \
   --resource-group rg-vm-mottu \
   --nsg-name nsgsr-mottu \
   --name port_5500 \
   --protocol tcp \
   --priority 1030 \
   --destination-port-range 5500
```

- Uma linha
```
az network nsg rule create --resource-group rg-vm-mottu --nsg-name nsgsr-mottu --name port_5500 --protocol tcp --priority 1030 --destination-port-range 5500
```

### Porta 8080

- Múltiplas linhas

```
az network nsg rule create \
   --resource-group rg-vm-mottu \
   --nsg-name nsgsr-mottu \
   --name port_8080 \
   --protocol tcp \
   --priority 1040 \
   --destination-port-range 8080
```

- Uma linha

```
az network nsg rule create --resource-group rg-vm-mottu --nsg-name nsgsr-mottu --name port_8080 --protocol tcp --priority 1040 --destination-port-range 8080
```
### Porta 3000

- Múltiplas linhas

```
az network nsg rule create \
   --resource-group rg-vm-mottu \
   --nsg-name nsgsr-mottu \
   --name port_3000 \
   --protocol tcp \
   --priority 1050 \
   --destination-port-range 3000
```

- Uma linha

```
az network nsg rule create --resource-group rg-vm-mottu --nsg-name nsgsr-mottu --name port_3000 --protocol tcp --priority 1050 --destination-port-range 3000
```
---
## ♻ 3º - Instalação e configuração do Docker e Git na VM

- Faça a conexão via SSH com sua VM.
### Instalação puxando o repositório do docker

```
sudo yum install -y yum-utils -y

```

```
sudo yum-config-manager --add-repo https://download.docker.com/linux/rhel/docker-ce.repo
```

```
sudo yum install docker-ce docker-ce-cli containerd.io docker-buildx-plugin docker-compose-plugin -y
```
```
sudo systemctl start docker
```
### Teste para ver se a instalação foi feita com sucesso

```
sudo docker ps -a
```
- Se não retornar nada, repita o processo
### Configuração para poder usar o docker sem o sudo

```
sudo usermod -aG docker admmottu
```
- Reinicie a conexão com a VM após usar o comando

### Instalação do Git
```
sudo yum install git -y
```
---
## 📦 4º - Containers

### Criação da rede
```
docker network create net-mottu
```
### Criação do Banco
```
docker container run -d --name oracle-db --network net-mottu -p 1521:1521 -p 5500:5500 --ulimit nofile=65535:65535 -e ORACLE_PWD=Oracle123 -v /home/admlnx/oracle_data:/opt/oracle/oradata container-registry.oracle.com/database/express:21.3.0-xe && sudo chown 54321:54321 /home/admlnx/oracle_data
```
- Leva em torno de 10 minutos para a criação completa do banco, para saber se já foi criado, use o comendo `docker ps -a`, se o container estiver em status "UP (Healty)", já pode ser usado.

### Puxando e rodando o APP
- Clona o repositório e já entra da pasta do app
```
git clone https://github.com/Yoshida672/BackEnd-Java-Mottu && cd BackEnd-Java-Mottu/monitoramento-mottu
```
- Builda o Dockerfile e rode o container
```
docker build -t app-mottu .
```
```
docker run -d --name app-mottu --network net-mottu -p 8080:8080 app-mottu
```
---
## ▶ 5º - CRUD

- GET
![image](https://github.com/user-attachments/assets/d7eb1a54-909b-4df0-948f-8d69d08e100a)

- POST
![image](https://github.com/user-attachments/assets/df211fda-5ce3-4167-854e-45aa24056741)
![image](https://github.com/user-attachments/assets/3aca9ba6-1854-4353-9700-1b7dd379887b)

- PUT
![image](https://github.com/user-attachments/assets/63aa7a89-6e72-4e71-adf8-69798892227c)
![image](https://github.com/user-attachments/assets/7b23ed0e-9114-4032-aa23-22bab6f0b191)

- DELETE
![image](https://github.com/user-attachments/assets/49fe9c45-bd79-43b4-8329-557b03fd14a5)
![image](https://github.com/user-attachments/assets/04924ac3-d5b7-4229-ae4e-a855573d4570)

---
## 🏦´6º - Arquitetura do projeto

![arquitetura](https://github.com/user-attachments/assets/5c08e83f-8a67-4242-a214-8253366fe3fd)

---
## 🗑 7º - Remoção da VM
```
az group delete --name rg-vm-mottu -y
```
---
