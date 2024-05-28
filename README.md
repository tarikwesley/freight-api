[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MONGO_BADGE]:https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white
[DOCKER_BADGE]:https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[SWAGGER_BADGE]:https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white
<p align="center">
<img src="src/main/resources/static/package-svgrepo-com.svg"></p>

<h1 align="center" style="font-weight: bold;">FREIGHT API<?xml version="1.0" ?></h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![mongo][MONGO_BADGE]
![docker][DOCKER_BADGE]
![swagger][SWAGGER_BADGE]

<p align="center">
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
 <a href="#colab">Collaborators</a> •
 <a href="#contribute">Contribute</a>
</p>

<p align="center">
  <b> Created to consult and calculate price and deadline freight. The api consume an external API being the <a href="https://viacep.com.br/"> ViaCep public API </a> to obtain the infos from zipcode. Java 8, Spring Boot, Mongodb and Docker were used for development.

Implemented for a SigaBem cargo transport company.

To calculate the total freight cost and expected delivery date.

Considering the following rules to calculate freight costs and delivery time:

- ZIPCODE's with the same DDDs have a 50% discount on freight and delivery is expected within 1 day
- ZIPCODE's in the same states have a 75% discount on freight and delivery in up to 3 days
- ZIPCODE's from different states should not be applied to the freight discount and expected delivery within 10 days
- The freight cost is charged according to the weight of the order, the value for each KG is R$ 1.00
- Your input must be “nameOfReceiver“, “zipCodeOrigin”, “zipCodeDestiny” and “weight”.

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

- [Docker](https://docs.docker.com/install/)
- [Git](https://git-scm.com/downloads)

<h3>Cloning</h3>

- After installing docker and git, clone the project with the following command:
    ```
    git clone https://github.com/tarikwesley/freight-api.git
    ```

<h3>Starting</h3>

- Access the cloned project directory.
  - Run the command ```docker-compose up -d``` to have docker start the project services in the background.
- When you obtain the following status below, you will be able to test the application.

```bash
 ✔ Network freight_network     Created
 ✔ Container freight-mongodb   Started
 ✔ Container freight-rest-api  Started
```

If the Mongo process ```freight-mongodb``` gives an error. Try disabling the mongo service on the local machine and try the previous command again.
Example: to disable the Mongo service on the local Linux machine:
Just run the command:
```sudo systemctl stop mongod```

Once everything is ok, continue the process.

- After this process, the API can be tested.
- To test API requests, you can use [Postman](https://www.getpostman.com/downloads/).
- To close the application use the command ``` docker-compose down```.

<h2 id="routes">📍 API Endpoints</h2>

<h3> 📍 Doc Swagger</h3>

``` http://localhost:8081/swagger-ui/index.html```


| Route                         | Description                                                                                          |
|-------------------------------|------------------------------------------------------------------------------------------------------|
| <kbd>POST /api/freight</kbd>  | Create a new consult from price and deadline freight. [Request details](#post-freight-detail)        |
| <kbd>GET /api/{zipcode}</kbd> | Returns zip code information for the informed zip code [Response details](#get-info-zipcode-detail") |


- <h3 id="post-freight-detail">POST /api/freight</h3>

- Request
```json
{
  "nameOfReceiver": "Nath",
  "zipCodeOrigin": "04884992",
  "zipCodeDestiny": "68440000",
  "weight": 20
}
```
- Response
```json
{
  "id": "6654a7b5036923075b66e46c",
  "nameOfReceiver": "Nath",
  "zipCodeOrigin": "04884992",
  "zipCodeDestiny": "68440000",
  "weight": 20.0,
  "valueOfFreightTotal": 20.00,
  "dateOfExpectedDelivery": "2024-06-06",
  "dateOfConsultation": "2024-05-27"
}
```

- <h3 id="get-info-zipcode-detail">GET /api/68440000</h3>

- Response 
```json
{
  "localidade": "Tucuruí",
  "cep": "68464-000",
  "uf": "PA",
  "ddd": "94"
}
```

<h2 id="colab">🤝 Collaborators</h2>

Special thank you for all people that contributed for this project.

<table>
  <tr>
    <td align="center">
      <a href="#">
        <img src="https://avatars.githubusercontent.com/u/47906316?v=4" width="100px;" alt="Tarik Wesley Profile Picture"/><br>
        <sub>
          <b>Tarik Wesley</b>
        </sub>
      </a>
    </td>

  </tr>
</table>

<h2 id="contribute">📫 Contribute</h2>

1. `git clone https://github.com/tarikwesley/freight-api.git`
2. `git checkout -b feature/NAME`
3. Follow commit patterns
4. Open a Pull Request explaining the problem solved or feature made, if exists, append screenshot of visual modifications and wait for the review!

<h3>Documentations that might help</h3>

[📝 How to create a Pull Request](https://www.atlassian.com/br/git/tutorials/making-a-pull-request)

[💾 Commit pattern](https://github.com/iuricode/padroes-de-commits)