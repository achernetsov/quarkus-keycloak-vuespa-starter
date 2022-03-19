<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { useSecurityStore } from '@/stores/security'

const robotsLoaded = ref(false)
const robots = ref([{name:'test'}])

onMounted(()=>{
  const security = useSecurityStore()

  var myHeaders = new Headers();
  myHeaders.append('Authorization', `Bearer ${security.keycloak.token}`);
  var requestOptions = {
    headers: myHeaders,
    };

  console.log('mounted, fetching robots...')
  fetch('/api/v1/robot', requestOptions)
    .then(response => response.json())
    .then(data => {
        robots.value = data
        robotsLoaded.value = true
      }
    )
    .catch(err=>console.error(err))
})

function removeRobot() {
  console.log("removing robot (not implemented)")
}
</script>

<template>

<div v-if="!robotsLoaded">
  <p>Robots are loading...</p>
</div>
<div v-else>
  <table>
    <th>
        <td>Name</td>
        <td>Actions</td>
    </th>
    <tr v-for="robot in robots">
        <td>{{robot.name}}</td>
        <td><button @click="removeRobot">X</button></td>
    </tr>
</table>
</div>

</template>