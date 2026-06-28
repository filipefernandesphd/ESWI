# Padrões de Projeto GoF

Materiais didáticos para três aulas de 45 minutos sobre padrões de projeto
GoF, com exemplos Java simples e slides em Slidev.

## Estrutura

```text
padroesdeprojeto/
├── factory-method/
│   ├── README.md
│   └── src/main/java/br/edu/padroes/factorymethod/
├── composite/
│   ├── README.md
│   └── src/main/java/br/edu/padroes/composite/
├── strategy/
│   ├── README.md
│   └── src/main/java/br/edu/padroes/strategy/
└── slides/
    ├── package.json
    └── slides.md
```

## Aulas

1. Introdução aos padrões GoF e Factory Method.
2. Composite.
3. Strategy e comparação final.

Cada pasta de padrão possui um exemplo executável pelo terminal, sem frameworks
externos, para manter o foco no conceito.

## Como compilar todos os exemplos

Execute a partir da raiz do repositório:

```bash
javac -d apps/padroesdeprojeto/factory-method/out $(find apps/padroesdeprojeto/factory-method/src/main/java -name "*.java")
javac -d apps/padroesdeprojeto/composite/out $(find apps/padroesdeprojeto/composite/src/main/java -name "*.java")
javac -d apps/padroesdeprojeto/strategy/out $(find apps/padroesdeprojeto/strategy/src/main/java -name "*.java")
```

## Como executar

```bash
java -cp apps/padroesdeprojeto/factory-method/out br.edu.padroes.factorymethod.Main
java -cp apps/padroesdeprojeto/composite/out br.edu.padroes.composite.Main
java -cp apps/padroesdeprojeto/strategy/out br.edu.padroes.strategy.Main
```

## Slides

Para rodar os slides localmente:

```bash
cd apps/padroesdeprojeto/slides
npm install
npm run dev
```

Para gerar o build:

```bash
cd apps/padroesdeprojeto/slides
npm run build -- --base "/<repo>/padroesdeprojeto-slides/"
```
