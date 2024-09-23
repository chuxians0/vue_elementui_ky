/** When your routing table is too long, you can split it into small modules **/

import Layout from '@/layout'

const tableRouter = {
  path: '/table',
  component: Layout,
  redirect: '/table/complex-table',
  name: 'Table',
  meta: {
    title: '会员管理',
    icon: 'table'
  },
  children: [
    // {
    //   path: 'dynamic-table',
    //   component: () => import('@/views/table/dynamic-table/index'),
    //   name: 'DynamicTable',
    //   meta: { title: 'Dynamic Table' }
    // },
    {
      path: 'complex-table',
      component: () => import('@/views/table/complex-table'),
      name: 'ComplexTable',
      meta: { title: '会员列表' }
    },

    // {
    //   path: 'inline-edit-table',
    //   component: () => import('@/views/table/inline-edit-table.vue'),
    //   name: 'InlineEditTable',
    //   meta: { title: '会员统计' }
    // },

    {
      path: 'drag-table',
      component: () => import('@/views/table/health.vue'),
      name: 'DragTable',
      meta: { title: '体检上传' }
    }
  ]
}
export default tableRouter
