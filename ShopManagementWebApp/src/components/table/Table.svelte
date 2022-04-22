<script lang="ts">
    import {TableHeader} from "./TableHeader";

    export let header: TableHeader<any> = TableHeader.fromArray([]);
    export let values: any[] = [];

    function checkIfRowEmpty(rowValue: any) {
        return rowValue == undefined || rowValue == null? "-": rowValue;
    }
</script>

<div class="shadow-dark-md rounded-2xl overflow-hidden bg-bg-50">
<table>
    <thead>
    <tr>
        {#each header.columns as column}
            <th class="border-0 border-b-2 border-black-alpha-800">
                {column.name}
            </th>
        {/each}
    </tr>
    </thead>

    <tbody>
    {#each values as value, i}
        <tr on:click={() => header.onRowClick(value)}>
            {#each header.columns as column}
                <td class="text-center border-l-2 border-black-alpha-400">
                    {checkIfRowEmpty(column.getValue(value))}
                </td>
            {/each}
        </tr>
    {/each}
    </tbody>

</table>
</div>

<style>

    table {
        @apply text-lg font-medium w-full ;
        border-collapse: collapse;
    }

    th {
        @apply font-bold;
    }

    th,td {
        @apply py-1 px-3;
    }

    tbody tr {
        @apply hover:bg-black-alpha-100 hover:cursor-pointer
    }


</style>