<script setup lang="ts">
import { useNav } from '@slidev/client'
import RodapeIF from './components/RodapeIF.vue'

const { currentPage, total, currentLayout } = useNav()
</script>

<template>
  <!-- Não exibe o rodapé na capa nem nos divisores de módulo. -->
  <RodapeIF
    v-if="currentLayout !== 'cover' && currentLayout !== 'module'"
    :page="currentPage"
    :total="total"
  />
</template>
