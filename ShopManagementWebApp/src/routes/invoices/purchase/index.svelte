<script lang="ts">



</script>


<div class=" widget-lg v-stack">
    <button class="center w-4/5 bg-bg-300 text-3xl btn-lg">+</button>

    <div class="table-wrapper">
        <table>
            <thead>
            <tr>
                <th>Business Entity</th>
                <th>Purchaser</th>

            </tr>
            </thead>

            <tbody>
            <tr><th>Zdzichu mpi</th><th>Bob Smith</th></tr>
        </tbody>
    </table>
    </div>
</div>

<style>
    table {
        /*border: 2px solid rgba(0,0,0,0.3);*/
        @apply text-lg font-medium;
        border-collapse: collapse;
    }

    td, th {
        @apply border-2 border-bg-400 font-medium p-1;
    }

    .table-wrapper {
        @apply rounded-md border-2 border-bg-400 overflow-hidden;
    }
</style>