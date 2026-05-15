# Digital Bank App

## Principais instruções

- Verifique se você possui os seguintes itens instalados no VSCode via Marketplace (estes não exigem admin):
  - `Extension Pack for Java`: (da Microsoft) Inclui suporte a Maven/Gradle e IntelliSense.
- Instale o plugin `Continue` no VSCode
- Verifique se o `Ollama` está instalado no computador

## Configuração da LLM local via Ollama

- Acesse o terminal e execute `ollama pull qwen2.5-coder:7b`
- Configure a extensão `Continue` do VSCode
  - Clique na opção `Configure models` (ícone de cubo)
  - Clique no botão de `+`
  - Selecione o provider como `Ollama` e selecione `Autodetect` em Model.
- Acesse o chat, escolha o modo `Chat` do `Continue`, selecione o modelo `qwen2.5-coder:7b (autodetected)` e envie o prompt para testar: `Olá! Você está rodando qual modelo?`
  - Obs: O modo `Agent` deve ser usado para ler e criar arquivos no projeto.
