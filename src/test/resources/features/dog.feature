Feature: Testes de API - Dog API

  @breeds
  Scenario Outline: Listar raças
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then valido a listagem de raças
    Examples:
      | url                  | endpoint           |
      | https://dog.ceo/api  | /breeds/list/all   |

  @breed-images
  Scenario Outline: Buscar imagens por raça
    Given que acesso a API "<url>"
    When realizo uma request GET especifica para "<endpoint>"
    Then valido as imagens da raça
    Examples:
      | url               				   | endpoint             |
      | https://dog.ceo/api  | /breed/ |

  @random
  Scenario Outline: Buscar imagem aleatória
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then valido a imagem aleatória retornada
    Examples:
      | url                  | endpoint               |
      | https://dog.ceo/api  | /breeds/image/random   |

  @negative
  Scenario Outline: Buscar raça inexistente
    Given que acesso a API "<url>"
    When realizo uma request GET para "<endpoint>"
    Then valido o retorno de erro para raça inválida
    Examples:
      | url                  | endpoint                     |
      | https://dog.ceo/api  | /breed/invalidbreed/images   |