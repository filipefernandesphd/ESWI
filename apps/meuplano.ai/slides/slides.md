---
theme: mint
title: Engenharia de Software I
info: |
  Template de slides da disciplina INF03082 — Engenharia de Software I.
  Tema Mint recolorido para o verde IF; dados vindos do .env.
class: text-center
transition: slide-left
mdc: true
layout: cover
---

<!--
Capa sobrescrita: todo o conteúdo (curso, disciplina, professor, foto, logo)
vem das variáveis de ambiente do .env via o composable useDisciplina().
Trocar o .env muda a capa sem editar este arquivo nem os componentes.
-->

---
layout: module
---

# Módulo 1 — Introdução

Divisor de seção · equivalente ao `moduleframe` do Beamer

---
layout: default
---

# Sobre este template

Base de slides da disciplina, montada sobre o tema **Mint** e recolorida para o
verde institucional do IF.

- **Capa, faixas e divisor** usam a paleta IF (`#2A9C41` → `#2B593F`)
- **Dados da disciplina** vêm do `.env` (prefixo `VITE_`)
- **Rodapé fixo** com `professor · disciplina · código` em todos os slides de conteúdo
- **Imagens** usam `BASE_URL` — não quebram no subcaminho do GitHub Pages

> Edite `.env` para adaptar a outra disciplina; os componentes não mudam.

---
layout: image-right
image: ./demo.svg
---

# Imagem ao lado

O layout `image-right` já vem pronto do tema Mint — basta informar `image:` no
frontmatter do slide.

- `image-left` / `image-right` para conteúdo + ilustração
- `imageClass` ajusta o enquadramento da imagem
- Ótimo para diagramas UML de análise e projeto

---
layout: default
---

# Bloco de código

Exemplo em Java — o eixo da disciplina é a evolução **análise → projeto → código**:

```java
public class PlanoDeAula {
    private final String titulo;
    private final List<String> objetivos;

    public PlanoDeAula(String titulo, List<String> objetivos) {
        this.titulo = titulo;
        this.objetivos = objetivos;
    }

    public String resumo() {
        return titulo + " (" + objetivos.size() + " objetivos)";
    }
}
```

---
layout: subsection
section: Módulo 1 — Introdução
subsection: 1.1 — Equivalências com o Beamer
---

Layout **opcional** `subsection`, para paridade com o `subsectionframe` do Beamer.

---
layout: squaredpaper
title: Rascunho (papel quadriculado)
---

Layout **opcional** `squaredpaper` — fundo quadriculado via CSS, para esboços e
diagramas à mão livre.

- Útil para rascunhar modelos antes de formalizar em UML
- Equivalente ao `squaredpaper` do Beamer

---
layout: default
---

# Mapeamento Beamer → Slidev

| Beamer | Template (Mint) |
|---|---|
| título | `cover` (sobrescrito via `.env`) |
| `moduleframe` | `module` |
| `subsectionframe` | `subsection` *(opcional)* |
| `squaredpaper` | `squaredpaper` *(opcional)* |
| `largeframe` | `default` / `end` |
| conteúdo + footline + `\logo` | `default` + `RodapeIF` |
| imagem ao lado | `image-left` / `image-right` |

**Observações de equivalência**

- **Auto-subseção:** o Beamer insere o divisor sozinho (`\AtBeginSubsection`);
  no Slidev use o layout `subsection` manualmente.
- **Navegação de seções no topo** (Madrid): sem equivalente — o Slidev traz a
  própria navegação.

---
layout: end
---

# Obrigado!

Engenharia de Software I · IF Sudeste MG — Campus Manhuaçu
