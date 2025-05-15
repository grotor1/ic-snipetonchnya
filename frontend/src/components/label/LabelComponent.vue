<template>
  <label v-if="!div" class="w-full flex flex-col">
    <div class="text-md mb-2 px-4">
      {{label}}
    </div>

    <slot/>
  </label>

  <div v-else class="w-full flex flex-col">
    <div class="text-md mb-2 px-4">
      {{label}}
    </div>

    <slot/>
  </div>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

interface IProps {
  label: string;
  div?: boolean;
}

defineProps<IProps>();
</script>

<style>

</style>
