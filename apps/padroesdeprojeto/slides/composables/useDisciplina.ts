export function useDisciplina() {
  const e = import.meta.env
  const base = import.meta.env.BASE_URL
  const asset = (p?: string) => (p ? base + p.replace(/^\//, '') : '')
  return {
    curso: e.VITE_CURSO ?? '',
    disciplina: e.VITE_NOME_DISCIPLINA ?? 'Disciplina',
    codigo: e.VITE_CODIGO_DISCIPLINA ?? '',
    professor: e.VITE_NOME_PROFESSOR ?? '',
    contato: e.VITE_CONTATO_PROFESSOR ?? '',
    instituicao: e.VITE_INSTITUICAO ?? '',
    foto: asset(e.VITE_FOTO_PROFESSOR),
    logo: asset(e.VITE_LOGO_INSTITUICAO),
  }
}
