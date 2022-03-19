import { defineStore } from 'pinia'

export const useSecurityStore = defineStore('security', {
  state: () => {
    return { keycloak: {} as any }
  }
})