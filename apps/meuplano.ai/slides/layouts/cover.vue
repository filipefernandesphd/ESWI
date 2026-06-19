<script setup lang="ts">
import { useDisciplina } from "../composables/useDisciplina";
const d = useDisciplina();
</script>

<template>
  <div
    class="grid grid-cols-2 gap-8 p-0 slidev-layout cover bg-gradient-to-tr gradient-primary"
  >
    <div class="relative flex items-center justify-center">
      <img
        v-if="d.foto"
        :src="d.foto"
        class="object-cover w-2/3 rounded shadow-xl aspect-[4/5]"
        alt=""
      />
    </div>
    <div class="flex flex-col justify-center w-full pr-10 text-white">
      <img v-if="d.logo" :src="d.logo" class="h-10 mb-6" alt="" />
      <p class="text-xl opacity-90">{{ d.codigo }} · {{ d.curso }}</p>
      <h1 class="text-6xl font-bold leading-tight">{{ d.disciplina }}</h1>
      <p class="mt-6 text-2xl font-semibold">
        <a
          v-if="d.contato"
          :href="d.contato"
          target="_blank"
          rel="noopener"
          class="text-white underline hover:opacity-80"
          >{{ d.professor }}</a
        >
        <template v-else>{{ d.professor }}</template>
      </p>
      <p class="mt-auto opacity-90">{{ d.instituicao }}</p>
    </div>
  </div>
</template>
