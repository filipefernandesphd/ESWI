<script setup lang="ts">
defineProps<{
  section?: string
  subsection?: string
}>()
</script>

<template>
  <div class="flex h-full slidev-layout subsection bg-white text-[#2B593F]">
    <div class="w-full px-16 my-auto text-center">
      <p v-if="section" class="text-xl tracking-wide uppercase opacity-70">
        {{ section }}
      </p>
      <div class="inline-block px-10 py-6 mt-4 border-2 border-[#2A9C41] rounded-lg">
        <h2 class="text-4xl font-bold">{{ subsection }}</h2>
      </div>
      <div class="mt-8 [&_*]:text-[#505050]">
        <slot />
      </div>
    </div>
  </div>
</template>
