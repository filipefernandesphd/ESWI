# public/

Coloque aqui os assets referenciados pelo `.env` (apenas o **nome do arquivo**):

- `VITE_FOTO_PROFESSOR` → ex.: `professor.jpg`
- `VITE_LOGO_INSTITUICAO` → ex.: `IFSudesteMG-logo.png`

O caminho final é montado com `BASE_URL` no composable `useDisciplina()`, então as
imagens funcionam tanto no `dev` quanto no subcaminho do GitHub Pages.

`demo.svg` é apenas um placeholder usado no slide de demonstração `image-right`.
