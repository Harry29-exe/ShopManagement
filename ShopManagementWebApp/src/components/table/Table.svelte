<script lang="ts">
    export let header: string[];
    export let values: any[][];

</script>

<table>
    <thead>
    <tr>
        {#each header as hCol}
            <th>{hCol}</th>
        {/each}
    </tr>
    </thead>

    <tbody>
    {#each values as row}
        <tr>
            {#each row as col}
                <td>{col}</td>
            {/each}
        </tr>
    {/each}
    </tbody>

</table>

<style>
    table {
        /*border: 2px solid rgba(0,0,0,0.3);*/
        @apply text-lg font-medium w-full;
        border-collapse: collapse;
    }

    thead tr {
        @apply bg-primary-100;
    }

    thead th {
        @apply border-0 border-b-2 border-primary-700;
    }

    td, th {
        @apply border-2 border-bg-400 font-medium p-1;
    }

    th {
        @apply font-bold;
    }

</style>