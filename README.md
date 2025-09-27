# 🌾 JavaFarm

JavaFarm é um jogo simples de **simulação de fazenda** desenvolvido em **Java com POO** e interface em **JavaFX**.  
O objetivo é plantar, colher e armazenar produtos no celeiro até atingir a capacidade máxima.

---

## 🚀 Funcionalidades

- Plantar **Batata, Cenoura e Morango** em terrenos (13 x 13).
- Cada produto cresce em ciclos diferentes, até o tamanho máximo (4).
- Colher os produtos quando estiverem prontos e armazenar no celeiro.
- O celeiro tem **capacidade limitada** e armazena 2 unidades por colheita.
- Barra de progresso mostra a ocupação do celeiro.
- Opção de **acelerar o tempo** para crescimento mais rápido.

---

## 🏗️ Estrutura de Classes

### Produtos
- `Produto` (classe abstrata)
    - `Batata`
    - `Cenoura`
    - `Morango`

Cada produto possui:
- `tamanho` (1 a 4)
- `tempoDeVida` (ciclos vividos, começa em 1)
- `tempoDeCrescimento` (define a cada quantos ciclos cresce)

Métodos principais:
- `crescer()`: incrementa o ciclo de vida e aumenta tamanho no tempo certo
- `podeColher()`: retorna `true` se tamanho == 4
- `getImagem()`: retorna o caminho da imagem

---

### Fazenda
- Contém uma matriz `Terreno[13][13]`
- Possui um `Celeiro`
- Permite plantar, colher e avançar ciclos

---

### Terreno
- Pode conter **apenas 1 produto por vez**
- Métodos:
    - `plantar(Produto p)`
    - `colher(Celeiro c)`
    - `estaOcupado()`

---

### Celeiro
- Armazena produtos até a capacidade máxima
- Métodos:
    - `armazenar(Produto p)` → adiciona 2 unidades
    - `consumir(String tipo)` → remove 1 unidade
    - `getOcupacao()` → porcentagem usada
    - `celeiroCheio()`

---

### Interface JavaFX
- **Grid 13x13** representando os terrenos
- Botões de seleção (Batata, Cenoura, Morango, Colher)
- Barra de progresso do celeiro
- Opção de acelerar o crescimento

---

## 📂 Estrutura de Diretórios

```
src/
 └── main/
     ├── java/br/com/joaocarloslima/
     │    ├── App.java          # Classe principal JavaFX
     │    ├── Controller.java   # Controlador da UI
     │    ├── Produto.java      # Classe abstrata
     │    ├── Batata.java
     │    ├── Cenoura.java
     │    ├── Morango.java
     │    ├── Terreno.java
     │    ├── Fazenda.java
     │    ├── Celeiro.java
     │    └── exceptions/
     │         ├── CeleiroCheioException.java
     │         ├── SemProdutoException.java
     │         └── TerrenoOcupadoException.java
     └── resources/br/com/joaocarloslima/
          ├── view.fxml
          └── images/
               └── screenshots/
                    ├── tela-colher.png
                    ├── tela-colhido.png
                    ├── tela-inicial.png
                    └── tela-plantando.png
               ├── batata1.png
               ├── batata2.png
               ├── batata3.png
               ├── batata4.png
               ├── batatas.png
               ├── cenoura1.png
               ├── cenoura2.png
               ├── cenoura3.png
               ├── cenoura4.png
               ├── free.png
               ├── icon.png
               ├── morango1.png
               ├── morango2.png
               ├── morango3.png
               ├── morango4.png
               ├── morangos.png
               └── terreno.png
```

---

## ▶️ Como Executar

1. Certifique-se de ter instalado:
    - [Java 17+](https://adoptium.net/)
    - [JavaFX SDK](https://openjfx.io/)

2. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/javafarm.git
   cd javafarm
   ```

3. Compile e execute com suporte ao JavaFX (exemplo usando Maven):
   ```bash
   mvn clean javafx:run
   ```

   ou, se estiver usando linha de comando manualmente:
   ```bash
   javac --module-path "PATH_DO_JAVAFX/lib" --add-modules javafx.controls,javafx.fxml src/main/java/br/com/joaocarloslima/*.java
   java --module-path "PATH_DO_JAVAFX/lib" --add-modules javafx.controls,javafx.fxml br.com.joaocarloslima.App
   ```

---

## 🎮 Controles

- Clique em **Batata**, **Cenoura** ou **Morango** e depois no terreno → planta.
- Clique em **Colher** e depois no terreno → colhe se o produto estiver pronto.
- **Checkbox "Acelerar"** → acelera o tempo de crescimento.

---

## 📊 Regras de Crescimento

- **Batata**: cresce 1 tamanho a cada 3 ciclos
- **Cenoura**: cresce 1 tamanho a cada 2 ciclos
- **Morango**: cresce 1 tamanho a cada ciclo
- Tamanho máximo = 4 (pode ser colhido)

---

## 📸 Screenshots
 
![Tela inicial](src/main/resources/br/com/joaocarloslima/images/screenshots/JavaFarm.gif)

---

## 👩‍💻 Autoria

Este projeto foi proposto pelo professor **[João Carlos Lima](https://github.com/joaocarloslima)**, que definiu o escopo e os requisitos da missão JavaFarm.

O desenvolvimento da aplicação (implementação das classes, integração com a interface JavaFX e ajustes finais) foi realizado por:
- **[Nicole Lins Coelho](https://github.com/Elociny)**
- **[Ana Paula Pereira Viana](https://github.com/AnaPaula2024)**  


