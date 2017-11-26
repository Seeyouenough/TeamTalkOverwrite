<template>
  <div class="dashboard-container">
    <component :is="currentRole"></component>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import adminDashboard from './admin/index'
import editorDashboard from './editor/index'

export default {
  name: 'dashboard',
  components: { adminDashboard, editorDashboard },
  data() {
    return {
      currentRole: 'adminDashboard'
    }
  },
  computed: {
    ...mapGetters([
      'introduction'
    ])
  },
  created() {
    /*if (this.introduction.indexOf('admin') >= 0) {
      return
    }*/
    this.currentRole = 'editorDashboard'
  }
}
</script>
