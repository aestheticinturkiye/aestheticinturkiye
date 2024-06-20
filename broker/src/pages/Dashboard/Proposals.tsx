import {
  Card,
  CardContent,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import {
  Table,
  TableBody,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { getProposals } from "@/services/Proposal";
import { useQuery } from "@tanstack/react-query";
import React from "react";

export const Proposals = () => {
  const { isPending, isError, data, error } = useQuery({
    queryKey: ["proposal"],
    queryFn: getProposals,
  });
  return (
    <Card x-chunk="dashboard-05-chunk-3">
      <CardHeader className="px-7">
        <CardTitle>Proposals</CardTitle>
        <CardDescription>Recent orders from your store.</CardDescription>
      </CardHeader>
      <CardContent>
        <Table>
          <TableHeader>
            <TableRow>
              <TableHead>Price</TableHead>
              <TableHead className="hidden sm:table-cell">
                Operation Date
              </TableHead>
              <TableHead className="hidden sm:table-cell">
                Accomodation
              </TableHead>
              <TableHead className="hidden md:table-cell">City</TableHead>
              <TableHead className="hidden md:table-cell">
                Transportation
              </TableHead>
              <TableHead className="hidden md:table-cell">Hospital</TableHead>
              <TableHead className="hidden md:table-cell">
                Description
              </TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data?.proposals?.map((request, index) => (
              <TableRow
                key={index}
                // onClick={() => setSelectedRow(request)}
                className="cursor-pointer"
              >
                <TableCell>{request.price}</TableCell>
                <TableCell className="hidden sm:table-cell">
                  {request.operationDate || "N/A"}
                </TableCell>
                <TableCell className="hidden md:table-cell">
                  {request.accommodation}
                </TableCell>
                <TableCell className="hidden md:table-cell">
                  {request.city}
                </TableCell>
                <TableCell className="hidden md:table-cell">
                  {request.transportation}
                </TableCell>
                <TableCell className="hidden md:table-cell">
                  {request.hospital}
                </TableCell>
                <TableCell className="hidden md:table-cell">
                  {request.description}
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  );
};
