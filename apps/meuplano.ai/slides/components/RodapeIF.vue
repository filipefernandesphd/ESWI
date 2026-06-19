<script setup lang="ts">
import { useDisciplina } from '../composables/useDisciplina'

defineProps<{
  page?: number
  total?: number
}>()

const d = useDisciplina()
</script>

<template>
  <footer
    class="absolute bottom-0 left-0 right-0 flex items-center gap-3 px-4 py-1.5 text-xs text-white bg-gradient-to-r gradient-primary"
  >
    <img v-if="d.logo" :src="d.logo" class="h-4" alt="" />
    <span class="truncate opacity-90">
      {{ [d.professor, d.disciplina, d.codigo].filter(Boolean).join(' · ') }}
    </span>
    <span v-if="page" class="ml-auto font-mono opacity-90">
      {{ page }}<template v-if="total"> / {{ total }}</template>
    </span>
  </footer>
</template>
