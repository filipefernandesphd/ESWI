<script setup lang="ts">
defineProps<{
  title?: string
}>()
</script>

<template>
  <div
    class="flex flex-col h-full slidev-layout squaredpaper"
    style="
      background-color: #fbfdfb;
      background-image:
        repeating-linear-gradient(0deg, rgba(42, 156, 65, 0.12) 0 1px, transparent 1px 32px),
        repeating-linear-gradient(90deg, rgba(42, 156, 65, 0.12) 0 1px, transparent 1px 32px);
    "
  >
    <h1 v-if="title" class="px-12 pt-10 text-4xl font-bold text-[#2B593F]">
      {{ title }}
    </h1>
    <div class="flex-1 px-12 py-6 text-[#505050]">
      <slot />
    </div>
  </div>
</template>
