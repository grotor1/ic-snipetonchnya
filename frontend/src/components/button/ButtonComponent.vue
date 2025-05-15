<template>
  <button :class="getClasses"
          :disabled>
    <slot />
  </button>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">

import { computed } from 'vue';

interface IProps {
  active?: boolean;
  fullWidth?: boolean;
  disabled?: boolean;
}

const props = withDefaults(defineProps<IProps>(), {
  fullWidth: true,
});

const getClasses = computed(() => [
  props.fullWidth && 'w-full',
  'transition-all border border-amber-100 px-4 py-1 rounded-lg active:scale-[102%] hover:cursor-pointer',
  'disabled:opacity-[0.6] disabled:hover:bg-stone-900 disabled:hover:text-amber-100 disabled:cursor-not-allowed',
  props.active ? 'bg-amber-100 text-stone-900' : 'bg-stone-900 hover:bg-amber-100 hover:text-stone-900',
]);
</script>

<style>

</style>
