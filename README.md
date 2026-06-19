# **ESWI**

Repositório de apoio à disciplina de **INF03082 - ENGENHARIA DE SOFTWARE I** do Curso de Bacharelado em Sistemas de Informação do IF Sudeste MG - Campus Manhuaçu.

## Estrutura do Projeto

```text
|-- apps
|   |-- digitalbank: projeto do banco digital implementado em Java
|   |-- meuplano.ai: projeto que constrói planos de aula com IA
|   |   |-- slides: apresentação Slidev (buildada no Pages)
|
|-- site
|   |-- index.html: hub do GitHub Pages (página inicial)
|
|-- prompts: prompts reutilizáveis de tarefas (*.prompt.md)
|
|-- docs
|   |-- uml: diagramas Mermaid (.mmd) de análise e projeto
|
|-- .github/workflows
|   |-- pages.yml: build do hub + slides e deploy no Pages
|
|-- README.md: visão geral do repositório
```

## Acesso via GitHub Pages

A página inicial do repositório fica publicada em:

**https://<usuario>.github.io/<repositorio>/**

A partir dela você acessa:

- **digitalbank** — código-fonte do banco digital (Java, terminal)
- **meuplano.ai › slides** — slides da aula (análise → projeto → código)
- **meuplano.ai › código-fonte** — gerador de planos de aula com IA (Ollama)

O site é publicado automaticamente a cada push na branch `main` pela GitHub
Action `.github/workflows/pages.yml`. Para ativar uma única vez:
**Settings → Pages → Build and deployment → Source: GitHub Actions**.
