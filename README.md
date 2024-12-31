# Moody - App de Análise de Sentimentos

**Moody** é um aplicativo em desenvolvimento para ajudar as pessoas a entenderem e acompanharem seus sentimentos de maneira simples e eficaz. Neste repositório, você encontrará o código-fonte de um app baseado em **Spring Boot** para backend e com integração futura com banco de dados.

## Tecnologias Usadas

- **Spring Boot**: Framework para desenvolvimento de APIs REST.
- **Hibernate Validator**: Para validação de dados de entrada.
- **PostgreSQL**: Banco de dados planejado para a próxima fase.
- **Git**: Controle de versão.

## Como Rodar o Projeto

1. **Clone o repositório**

   Se ainda não tiver o repositório em sua máquina, clone-o utilizando o Git:

   ```bash
   git clone https://github.com/Jefferson-F/Moodly
   ```

2. **Instalar as dependências**

   Para garantir que todas as dependências do projeto sejam instaladas, execute o seguinte comando:

   ```bash
   mvn clean install
   ```

   Este comando irá baixar as dependências necessárias e compilar o projeto.

3. **Executar o projeto**

   Após a instalação das dependências, você pode rodar o projeto com o seguinte comando:

   ```bash
   mvn spring-boot:run
   ```

   Isso iniciará o servidor em `localhost:8080`.

4. **Testar a aplicação**

   A primeira rota disponível é a de **cadastro de usuários**. Para testar, faça um **POST** para o endpoint `/api/register` enviando um JSON com email e senha:

   ```json
   {
     "email": "exemplo@email.com",
     "password": "senha123"
   }
   ```

   Se tudo estiver correto, você verá a resposta:

   ```json
   "Usuário registrado com sucesso!"
   ```

## Aprendizados e Desafios

À medida que o projeto evolui, vou atualizando este README com os principais aprendizados e desafios que surgem durante o desenvolvimento:

### Configuração do Spring Boot e Integração com APIs
A primeira experiência com o **Spring Boot** foi incrível. O framework facilita muito a criação de APIs, tornando o processo de configurar endpoints e testar funcionalidades mais ágil.

### Validações e Anotações
Comecei a lidar com validações de dados usando o `@Valid`, o que me permitiu garantir que os dados enviados pelos usuários estejam no formato correto. Tivemos alguns desafios com versões de dependências, mas a resolução trouxe aprendizado sobre como o Spring Boot lida com dependências de forma automática.

### Trabalhando com JSON e Formatação de Dados
Trabalhar com dados em **JSON** para cadastro de usuários foi uma ótima maneira de praticar e entender como o **Spring Boot** lida com a serialização e desserialização de objetos.

##Melhorias Futuras

- **Banco de Dados**: O próximo passo será integrar um banco de dados PostgreSQL para persistência dos dados.

---

## Contato

Se tiver dúvidas ou sugestões, entre em contato!

- **Email**: jeffersonferreira070@email.com
- **LinkedIn**: www.linkedin.com/in/jefferson-ferreira-44148a219

```
