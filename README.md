# SPRINT - CHALLENGE

## GRUPO DE RECURSOS

```
az group create --name rg-vm-mottu --location brazilsouth
```

## CRIAÇÃO DA VM

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

## Expondo portas

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

### Porta 22

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

### Porta 8080

- Múltiplas linhas

```
az network nsg rule create \
   --resource-group rg-vm-mottu \
   --nsg-name nsgsr-mottu \
   --name port_8080 \
   --protocol tcp \
   --priority 1030 \
   --destination-port-range 8080
```

- Uma linha

```
az network nsg rule create --resource-group rg-vm-mottu --nsg-name nsgsr-mottu --name port_8080 --protocol tcp --priority 1030 --destination-port-range 8080
```

## Instalação e configuração do Docker na VM

#### Instalação puxando o repositório do docker

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
#### Teste para ver se a instalação foi feita com sucesso

```
sudo docker run hello-world
```

#### Configuração para poder usar o docker sem o sudo

```
sudo usermod -aG docker admmottu
```
Reinicie a conexão com a VM após usar o comando

## DELETANDO A VM

```
az group delete --name rg-vm-mottu -y
```
