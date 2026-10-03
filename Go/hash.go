package something

import "hash/crc32"

func foo(x uint32) *crc32.Table {
	return crc32.MakeTable(x)
}
