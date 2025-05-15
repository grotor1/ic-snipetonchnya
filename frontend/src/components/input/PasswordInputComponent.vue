<template>
  <input v-model="value"
         type="password"
         class="w-full border border-stone-700 bg-stone-900 px-4 py-1 rounded-lg placeholder:text-opacity-50 focus:border-stone-500 outline-0"
         :placeholder>
</template>

<script lang="ts">
export default {};
</script>

<script setup lang="ts">


import { computed } from 'vue';

interface IEmits {
  (event: 'update:modelValue', value: string): void;
}

interface IProps {
  modelValue: string;

  placeholder?: string;
}

const emits = defineEmits<IEmits>();
const props = defineProps<IProps>();

const value = computed({
  get: () => props.modelValue,
  set: (value: string) => emits('update:modelValue', value),
});
</script>

<style>

</style>
